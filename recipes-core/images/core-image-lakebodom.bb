SUMMARY = "An image that will boot on a webapp."

LICENSE = "BSD"

IMAGE_FEATURES += "ssh-server-dropbear hwcodecs"

DISTRO_FEATURES += "pulseaudio opengl wifi pci"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    liberation-fonts \
    connman \
    connman-client \
    kernel-modules \
    linux-firmware \
    wireless-tools \
    wpa-supplicant \
    crosswalk \
    crosswalk-example \
    emberwind \
    hexgl \
    jquerymobile-demos \
    webgl-motion-detector \
    webrtc \
    "

inherit core-image
