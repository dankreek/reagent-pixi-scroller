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

(defn scroller-layer [image]
  [TilingSprite {:image image
                 :width width
                 :height height}])

(defn scroller []
  [Container
   [scroller-layer "layer5.png"]
   [scroller-layer "layer4.png"]
   [scroller-layer "layer3.png"]
   [scroller-layer "layer2.png"]
   [scroller-layer "layer1.png"]])

(defn scroller-stage []
  [Stage {:width width
          :height height}
   [scroller]])

(r/render-component [scroller-stage]
                    (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
