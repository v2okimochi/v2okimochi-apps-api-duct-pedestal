(ns v2okimochi-apps-api.handler.sample-responses
  "レスポンスのサンプル"
  (:require [ring.util.http-response :as response]))

(defn root-response
  "ステータスコード200でmapを返す"
  [options]
  (response/ok {:okimochi "文字"
                :日本語も使える 10
                :†記号も使える† "aiue"}))

(defn bad-request
  "400 Bad Requestのmapを返す"
  [options]
  (response/bad-request {:okimochi-error "エラー文ですよね"}))

(defn okimochi-response
  "mapを返す"
  [{:keys [db tx-data]}]
  (response/ok {:data {:okimochi "おきもちv2"
                       :v2 2
                       :status false}
                :requests{:description "okimochi-responseで受け取ったdbやtx-dataはこれ"
                          :db (str db)
                          :tx-data tx-data}}))
