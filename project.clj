(defproject v2okimochi-apps-api "1.0.0"
  :description "ductとpedestalを使ったルーティング処理"

  ;; 依存関係の管理（利用するライブラリはバージョンと共に指定する）
  :dependencies [[camel-snake-kebab "0.4.0"]
                 [duct/core "0.8.0"]
                 [duct.module.cambium "1.1.0"]
                 [duct.module.pedestal "2.0.2"]
                 [duct/module.sql "0.5.0"]
                 [honeysql "0.9.8"]
                 [metosin/ring-http-response "0.9.1"]
                 [org.clojure/clojure "1.10.1"]
                 [org.mariadb.jdbc/mariadb-java-client "2.5.1"]]
  :plugins [[duct/lein-duct "0.12.1"]]

  ;; leinバージョンの下限を指定 (herokuにlein 1.xを使わせないため)
  :min-lein-version "2.0.0"

  ;; ソースコードの場所を指定する
  :source-paths   ["src/clj"]
  :resource-paths ["resources"]

  ;; jarの名前を指定する
  :uberjar-name "v2okimochi-apps-api-standalone.jar"

  :main v2okimochi-apps-api.main

  :middleware     [lein-duct.plugin/middleware]
  :profiles {:repl {:prep-tasks   ^:replace ["javac" "compile"]
                    :repl-options {:init-ns user}}
             :dev [:project/dev]
             :project/dev {:source-paths ["dev/src"]
                           :resource-paths ["dev/resources"]
                           :dependencies [[fipp "0.6.21"]
                                          [hawk "0.2.11"]
                                          [integrant/repl "0.3.1"]]}
             :profiles/dev {}})
