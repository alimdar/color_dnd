(ns color_dnd.core
  (:require
    [reagent.core :as r :refer [atom]]
    [reagent.dom :as d]))

(def text-value (atom "Hi there!"))
(def text-color (atom "#6bb3f5"))
(def dragged-color (atom nil))

(defn allow-drop [e]
  (.preventDefault e))

(defn drop-color []
  (if @dragged-color
    (#(reset! text-color @dragged-color)))
  )

(defn color-dot [initColor]
  (let [color-val (atom initColor)]
    (fn []
      [:div.color-circle {
                           :draggable     "true"
                           :on-drag-start #(reset! dragged-color @color-val)
                           :on-drag-end #(reset! dragged-color nil)}
       [:input.color-picker
        {:type      "color"
         :value     @color-val
         :on-change #(reset! color-val (-> % .-target .-value))}]
       [:div.color-display {:style {:backgroundColor @color-val}}]]
      )
    )
  )

(defn color-palette []
  (let [color-list #js["#68dec3" "#ffc156" "#b792ff"]]
    [:div.color-palette
     (for [color color-list]
       ^{:key color} [color-dot color])]))


(defn drop-area []
  [:div.drop-area
   {:on-drag-over  allow-drop
    :on-drag-enter allow-drop
    :on-drop       drop-color}
   [:div.large-text {:style {:color @text-color}} @text-value]])

(defn input-field []
  [:input.text-input
   {:type      "text"
    :value     @text-value
    :on-change #(reset! text-value (-> % .-target .-value))}])

(defn home-page []
  [:div
   [:div.title "Drag and drop dots on text area to change the color"]
   [color-palette]
   [drop-area]
   [:div.footer [input-field]
    [:div.title "Type here to change the text"]]])

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
