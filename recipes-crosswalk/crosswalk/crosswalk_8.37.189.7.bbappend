FILESEXTRAPATHS_prepend := "${THISDIR}/crosswalk"

SRC_URI += "\
    file://do_not_link_webrtc_with_x11.patch;patch=1 \
    file://embedded_chromium_crosswalk.patch;patch=1 \
    file://embedded_crosswalk.patch;patch=1 \
    "

DEPENDS_remove = "gtk+"
DEPENDS_remove = "libxss"
DEPENDS += "virtual/egl"

RDEPENDS_crosswalk += "\
    libdricore \
    libegl-mesa \
    libgbm \
    libgl-mesa \
    libglapi \
    libgles1-mesa \
    libgles2-mesa \
    mesa-driver-i915 \
    mesa-driver-i965 \
    "

DEFAULT_CONFIGURATION += "\
    -Dembedded=1 \
    -Dozone_platform_gbm=1 \
    -Dozone_platform_wayland=0 \
    -Duse_ozone=1 \
    -Duse_udev=1 \
    "
