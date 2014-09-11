FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://iwlwifi.cfg"

module_autoload_iwlwifi = "iwlwifi"
