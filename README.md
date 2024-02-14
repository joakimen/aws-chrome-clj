> [!WARNING]
> Superseded by https://github.com/joakimen/awsvault.clj

# aws-chrome-clj

[![lint](https://github.com/joakimen/aws-chrome-clj/actions/workflows/lint.yml/badge.svg)](https://github.com/joakimen/aws-chrome-clj/actions/workflows/lint.yml)

Opens a sandboxed (cache/data) Google Chrome instance for the specified AWS Account, using [99designs/aws-vault](https://github.com/99designs/aws-vault).

## requirements

- macOS
- Google Chrome
- https://github.com/babashka/babashka
- https://github.com/99designs/aws-vault

## install

```sh
bbin install https://raw.githubusercontent.com/joakimen/aws-chrome-clj/master/aws_chrome.clj
```

## run

```sh
aws-chrome <profile>
```

`<profile>` is one of the profiles defined in your `~/.aws/config`.
