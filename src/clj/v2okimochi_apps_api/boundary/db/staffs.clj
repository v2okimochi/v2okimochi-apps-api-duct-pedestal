(ns v2okimochi-apps-api.boundary.db.staffs
  (:require [clojure.java.jdbc :as jdbc]
            [duct.database.sql]
            [honeysql.core :as sql]
            [v2okimochi-apps-api.util.core :as util]))

(defprotocol Staffs
  "第一引数の型によって処理を分ける"
  (find-all-staffs [db]))

(def sql-staff
  "社員一覧を選択するSQL文 (SELECT v.* FROM staff v)"
  (sql/build
   :select [:v.*]
   :from [[:staff :v]]))

(defn select
  "sql-mapとして受け取ったクエリの実行結果をkebab-caseで返す"
  [{:keys [spec]} sql-map]
  (jdbc/query spec (sql/format sql-map :quoting :mysql)
              {:identifiers util/->kebab-case}))

(extend-protocol Staffs
  duct.database.sql.Boundary
  (find-all-staffs [db]
    ;; DBにsql-staffのSQL文を送り、社員一覧を返す
    (select db sql-staff)))
