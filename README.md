# aws-chrome-clj

Opens a sandboxed (cache/data) Google Chrome instance for the specified AWS Account, using [99designs/aws-vault](https://github.com/99designs/aws-vault).

## Install

```sh
bbin install https://raw.githubusercontent.com/joakimen/aws-chrome-clj/master/aws_chrome.clj
```

## Run

```sh
aws-chrome <profile>
```

`<profile>` is one of the profiles defined in your `~/.aws/config`.
