(ns reagent-pixi-scroller.core
  (:require [reagent.core :as r]
            [cljsjs.react-pixi]))

(enable-console-print!)

(def width 636)
(def height 480)

(def Stage (r/adapt-react-class js/ReactPIXI.Stage))
(def Container (r/adapt-react-class js/ReactPIXI.DisplayObjectContainer))
(def TilingSprite (r/adapt-react-class js/ReactPIXI.TilingSprite))

(defonce app-state (r/atom {:scroll-position 0}))

(defn scroller-layer [image rate]
  [TilingSprite {:image image
                 :width width
                 :height height
                 :tile-position [(* rate (@app-state :scroll-position)) 0]}])

(defn scroller []
  (r/with-let [request-id (atom nil)
               _ ((fn this []
                    (reset! request-id (js/requestAnimationFrame this))
                    (swap! app-state update-in [:scroll-position] inc)))]
    [Container
     [scroller-layer "layer5.png" 0.025]
     [scroller-layer "layer4.png" 0.25]
     [scroller-layer "layer3.png" 0.5]
     [scroller-layer "layer2.png" 0.75]
     [scroller-layer "layer1.png" 1.0]]
    (finally
      (js/cancelAnimationFrame @request-id))))

(defn scroller-stage []
  [Stage {:width width
          :height height}
   [scroller]])

(r/render [scroller-stage]

          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;;(swap! app-state update-in [:__figwheel_counter] inc)
  )

