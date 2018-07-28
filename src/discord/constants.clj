(ns discord.constants
  (:require [clojure.math.numeric-tower :as math]))

;; Units of time
(defonce SECONDS-IN-MINUTE 60)
(defonce SECONDS-IN-HOUR (* 60 SECONDS-IN-MINUTE))
(defonce SECONDS-IN-DAY (* 24 SECONDS-IN-HOUR))

(defonce MILLISECONDS-IN-SECOND 1000)
(defonce MILLISECONDS-IN-MINUTE (* MILLISECONDS-IN-SECOND SECONDS-IN-MINUTE))
(defonce MILLISECONDS-IN-HOUR (* MILLISECONDS-IN-SECOND SECONDS-IN-HOUR))

(def permissions [:create-instant-invite
                  :kick-members
                  :ban-members
                  :administrator
                  :manage-channels
                  :manage-guild
                  :add-reactions
                  :view-audit-log
                  :view-channel
                  :send-messages
                  :send-tts-messages
                  :manage-messages
                  :embed-links
                  :attach-files
                  :read-message-history
                  :mention-everyone
                  :use-external-emojis
                  :connect
                  :speak
                  :mute-members
                  :deafen-members
                  :move-members
                  :use-vad
                  :change-nickname
                  :manage-roles
                  :manage-webhooks
                  :manage-emojis])

(defn permissions-bits
  "Given a set of permissions (see the `permissions` vector in this namespace),
  returns the integer representing that set of permissions. For example:

  => (permissions-bits #{:kick-members :administrator}) ; 2 + 8
  10"
  [perm-set]
  {:pre [(set? perm-set)]}
  (transduce (comp (map-indexed vector)
                   (keep (fn [[idx perm-key]]
                           (when (perm-set perm-key)
                             (math/expt 2 idx)))))
             +
             permissions))

(def all-perms (permissions-bits (set permissions)))
