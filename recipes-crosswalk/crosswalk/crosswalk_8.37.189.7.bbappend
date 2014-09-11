FILESEXTRAPATHS_prepend := "${THISDIR}/crosswalk"

SRC_URI += "\
    file://do_not_link_webrtc_with_x11.patch;patch=1 \
    file://embedded_chromium_crosswalk.patch;patch=1 \
    file://embedded_crosswalk.patch;patch=1 \
    "

DEPENDS_remove = "gtk+"
DEPENDS_remove = "libxss"
DEPENDS += "mesa"

RDEPENDS_crosswalk += "\
    mesa-driver-i915 \
    mesa-driver-i965 \
    libegl-mesa \
    libgbm \
    libgl-mesa \
    libglapi \
    libgles1-mesa \
    libgles2-mesa \
    "

DEFAULT_CONFIGURATION += "\
    -Dchromeos=0 \
    -Dembedded=1 \
    -Dozone_platform_dri=1 \
    -Dozone_platform_gbm=1 \
    -Dozone_platform_wayland=0 \
    -Duse_ozone=1 \
    -Duse_udev=1 \
    -Duse_x11=0 \
    "
