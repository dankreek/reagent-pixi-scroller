(ns reagent-pixi-scroller.core
  (:require [reagent.core :as r :refer [atom]]
            [cljsjs.react-pixi]))

(enable-console-print!)

(def width 636)
(def height 480)

(def Stage (r/adapt-react-class js/ReactPIXI.Stage))
(def Container (r/adapt-react-class js/ReactPIXI.DisplayObjectContainer))
(def TilingSprite (r/adapt-react-class js/ReactPIXI.TilingSprite))

(defonce app-state (atom {:text "Hello world!"}))

(defn scroller-stage []
  [Stage {:width width
          :height height}])

(r/render-component [scroller-stage]
                    (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
