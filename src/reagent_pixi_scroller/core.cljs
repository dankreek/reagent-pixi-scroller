(ns reagent-pixi-scroller.core
  (:require [reagent.core :as r :refer [atom]]
            [cljsjs.react-pixi]))

(enable-console-print!)

(def Stage (r/adapt-react-class js/ReactPIXI.Stage))
(def Container (r/adapt-react-class js/ReactPIXI.DisplayObjectContainer))
(def TilingSprite (r/adapt-react-class js/ReactPIXI.TilingSprite))

(defonce app-state (atom {:text "Hello world!"}))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
