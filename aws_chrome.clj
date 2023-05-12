(ns aws-chrome
  "open an isolated chrome instance with the specified aws-profile"
  (:require [babashka.process :as p]
            [clojure.string :as str]
            [babashka.fs :as fs]
            [babashka.cli :as cli]))

(def chrome-path "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome")

(defn parse-opts [opts]
  (cli/parse-opts opts {:require [:profile]
                        :validate {:profile #(not (str/blank? %))}
                        :args->opts [:profile]}))

(defn get-data-dir [profile]
  (str (fs/path (fs/xdg-data-home) "aws_chrome" profile)))

(defn get-cache-dir [profile]
  (str (fs/path (fs/xdg-cache-home) "aws_chrome" profile)))

(defn create-login-link [profile]
  (let [{:keys [out err exit]} (p/sh "aws-vault" "login" profile "--stdout" "--prompt" "osascript")]
    (if (zero? exit)
      (str/trim out)
      (throw (ex-info (str/trim err) {:babashka/exit exit})))))

(defn -main [& args]
  (when-not (fs/executable? chrome-path)
    (throw (ex-info (str "couldn't find chrome executable at expected path: " chrome-path) {:babashka/exit 1})))

  (when-not (fs/which "aws-vault")
    (throw (ex-info "aws-vault is not installed" {:babashka/exit 1})))

  (let [{:keys [profile]} (parse-opts args)
        user-data-dir (get-data-dir profile)
        disk-cache-dir (get-cache-dir profile)
        aws-login-url (create-login-link profile)]
    (p/process chrome-path
               "--no-first-run"
               "--start-maximized"
               "--new-window"
               (str "--user-data-dir=" user-data-dir)
               (str "--disk-cache-dir=" disk-cache-dir)
               aws-login-url)))

(apply -main *command-line-args*)
