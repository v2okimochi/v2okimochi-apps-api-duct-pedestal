(ns user)

(defn dev
  "dev名前空間に移動して `:loaded`って返すだけ"
  []
  (require 'dev)
  (in-ns 'dev)
  :loaded)
