(ns v2okimochi-apps-api.routes
  "ルーティング"
  (:require [integrant.core :as ig]
            [io.pedestal.http :as http]
            [io.pedestal.http.body-params :as body-params]
            [io.pedestal.http.route :as route]
            [v2okimochi-apps-api.handler.sample-responses :as sample]
            [v2okimochi-apps-api.handler.staffs :as staffs]
            [v2okimochi-apps-api.interceptors :as interceptors]))


;; reset時（のinit処理時）に更新する場所を指定？
(defmethod ig/init-key ::routes
  [_ {:keys [db]}]

  ;; ルーティング時、request mapをインターセプターに渡す
  ;; `common-interceptors`に入っている順にrequestを渡し、逆順にresponseが返っていく
  ;; `json-body`によってmapはJSONにして返す
  (let [common-interceptors [(body-params/body-params)
                             http/json-body
                             interceptors/attach-tx-data
                             (interceptors/attach-database db)]]
    #(route/expand-routes
      #{["/api/" :get
         ;; GET requestに対して `sample-responses/root-response`関数からレスポンスを返す
         (conj common-interceptors `sample/root-response)]

        ["/api/bad-request" :get
         (conj common-interceptors `sample/bad-request)]

        ["/api/okimochi" :get
         (conj common-interceptors `sample/okimochi-response)]

        ["/api/staffs" :get
         (conj common-interceptors `staffs/list-staffs)]})))
