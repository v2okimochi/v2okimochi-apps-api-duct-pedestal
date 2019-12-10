(ns dev
  "開発用の名前空間"
  (:require [clojure.java.io :as io]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.repl :refer :all]
            [duct.core :as duct]
            [duct.core.repl :as duct-repl]
            [integrant.core :as ig]
            [integrant.repl :refer [clear halt go init prep reset]]
            [integrant.repl.state :refer [config system]]))

;; `duct_hierarchy.edn`ファイルを使ってglobal derive hierarchyを拡張する
;; (アプリ起動時に1回呼ぶ必要がある)
(duct/load-hierarchy)

(defn read-config []
  (duct/read-config (io/resource "v2okimochi_apps_api/config.edn")))

(defn auto-reset
  "`duct-repl/auto-reset`を呼んでるだけ"
  []
  (duct-repl/auto-reset))

;; もし `local.clj`があれば、その内容も読み込む
(when (io/resource "local.clj")
  (load "local"))

(def profiles
  [:duct.profile/dev :duct.profile/local])

(clojure.tools.namespace.repl/set-refresh-dirs "dev/src" "src")

(integrant.repl/set-prep! #(duct/prep-config (read-config) profiles))
