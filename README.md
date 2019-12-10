# v2okimochi-apps-api

# 概要
Herokuで動くClojureのREST APIをDuct + Pedestal構成で作ってみたアレ。こっそりCambiumも入ってる。


# 何するものぞAPI
- DBと繋いでるだけのREST API
- ローカルではDocker上のMariaDBと接続し、HerokuではHeroku add-onsのJawsDB Mariaと接続する


# 主な依存関係

- [duct/core 0.8.0](https://github.com/duct-framework/core)
  - 0.8.0 から `(auto-reset)`が使えるようになった 🎉
  - `(auto-reset)`するとファイル保存時に自動で `(reset)`される
  - なんと `(suto-reset)`を2段重ねできる (なおresetされた瞬間に壊れてREPL再起動を余儀なくされる模様)
- [duct.module.pedestal 2.0.2](https://github.com/lagenorhynque/duct.module.pedestal)
  - ルーティング機能を担う (インターセプター方式になってる)
- [duct.module.cambium 1.1.0](https://github.com/lagenorhynque/duct.module.cambium)
  - ログ関連をいい感じにやってくれる (丸投げ)
- org.mariadb.jdbc/mariadb-java-client 2.5.1
  - DBはMariaDBを使うので


# サーバ準備と起動
- JDK (12確認済み)をインストール
- [Leiningen](https://leiningen.org/)をインストール
- Dockerをインストール
- `v2okimochi-apps-api/`直下で `docker-compose up -d`してコンテナ作成・起動
- ClojureのREPLを起動 (Spacemacsなら `.clj`ファイル内で `, '`)
- REPL上から `(dev)`でdev namespaceに移動し、 `(reset)`でサーバ起動

ローカルURLは `localhost:9001/api/`に設定してある。
