(ns cljss.rum
  (:require [cljss.core :refer [->styled var->cls-name var->cmp-name]]))

(defmacro defstyled [var tag styles]
  (let [cls-name# (var->cls-name var)
        cmp-name# (var->cmp-name var)
        [tag# static# vals# attrs#] (->styled tag styles cls-name#)
         create-element# `#(apply js/React.createElement ~tag# (cljs.core/clj->js %1) (sablono.core/html %2))]
    `(do
       (def ~var
         (cljss.rum/styled ~cls-name# ~static# ~vals# ~attrs# ~create-element#))
       (set! ~var ~'-displayName ~cmp-name#))))
