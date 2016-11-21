(ns reagent-pixi-scroller.core
  (:require [reagent.core :as r :refer [atom]]
            [cljsjs.react-pixi]))

(enable-console-print!)

(def width 636)
(def height 480)

(def Stage (r/adapt-react-class js/ReactPIXI.Stage))
(def Container (r/adapt-react-class js/ReactPIXI.DisplayObjectContainer))
(def TilingSprite (r/adapt-react-class js/ReactPIXI.TilingSprite))

(defonce app-state (atom {:scroll-position 0}))

(defn scroller-layer [image rate]
  [TilingSprite {:image image
                 :width width
                 :height height
                 :tile-position [(* rate (@app-state :scroll-position)) 0]}])

(defn scroller []
  [Container
   [scroller-layer "layer5.png" 0.025]
   [scroller-layer "layer4.png" 0.25]
   [scroller-layer "layer3.png" 0.5]
   [scroller-layer "layer2.png" 0.75]
   [scroller-layer "layer1.png" 1.0]])

(defn scroller-stage []
  [Stage {:width width
          :height height}
   [scroller]])

(r/render-component [scroller-stage]
                    (. js/document (getElementById "app")))

(defn animation-loop! []
  (let [cur-counter (:__figwheel_counter @app-state)
        loop-fn (fn this []
                  (when (= cur-counter (:__figwheel_counter @app-state))
                    (js/requestAnimationFrame this)
                    (swap! app-state update-in [:scroll-position] inc)))]
    (loop-fn)))

(animation-loop!)

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc)
  (animation-loop!))

