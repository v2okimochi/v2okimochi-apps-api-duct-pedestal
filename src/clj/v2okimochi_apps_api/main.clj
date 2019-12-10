(ns v2okimochi-apps-api.main
  "herokuでビルドされるmain名前空間"
  (:gen-class)
  (:require [duct.core :as duct]))

(duct/load-hierarchy)

(defn -main [& args]
  (let [keys     (or (duct/parse-keys args) [:duct/daemon])
        profiles [:duct.profile/prod]]
    (-> (duct/resource "v2okimochi_apps_api/config.edn")
        (duct/read-config)
        (duct/exec-config profiles keys))))
