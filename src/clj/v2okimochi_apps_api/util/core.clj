(ns v2okimochi-apps-api.util.core
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :refer [transform-keys]]))

(defn ->kebab-case
  "_を-に置き換える"
  [v]
  (csk/->kebab-case v :separator \_))

(defn transform-keys-to-kebab
  "コレクションのkeyをkebab-caseに置き換える"
  [coll]
  (transform-keys ->kebab-case coll))
