(ns v2okimochi-apps-api.handler.staffs
  "社員データ"
  (:require [ring.util.http-response :as response]
            [v2okimochi-apps-api.boundary.db.staffs :as db.staffs]))

(defn list-staffs
  "社員リストを返す"
  [{:keys [db]}]
  (let [data {:data (db.staffs/find-all-staffs db)}]
    (response/ok data)))
