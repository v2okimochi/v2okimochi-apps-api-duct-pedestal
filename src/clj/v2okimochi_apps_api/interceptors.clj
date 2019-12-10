(ns v2okimochi-apps-api.interceptors
  (:require [io.pedestal.interceptor.helpers :as interceptor]
            [ring.util.response :as ring-response]
            [v2okimochi-apps-api.util.core :as util]))

(def attach-tx-data
  {:name ::attach-tx-data
   :enter
   (fn [context]
     ;; クエリパラメータやJSONパラメータを一時的にkebab-caseに変換し、:tx-dataとして入れ直す
     (let [params (merge (get-in context [:request :query-params])
                         (get-in context [:request :json-params]))]
       (assoc-in context [:request :tx-data] (util/transform-keys-to-kebab params))))})

(defn attach-database
  "context内にある :request内の :dbを置き換える"
  [db]
  {:name ::attach-database
   :enter
   (fn [context]
     (assoc-in context [:request :db] db))})
