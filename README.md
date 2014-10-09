# Introduction

Lake Bodom is the minimal Linux distribution that boots a fully-fledged Web
application.

This repository is actually a Yocto layer and groups all the needed components
that form Lake Bodom, featuring mostly Crosswalk Application run-time and
Chromium. In particular Chromium is using Ozone-GBM platform and therefore
does not rely on any Linux traditional window system like X11, Wayland, etc,
neither graphics toolkits like EFL, Qt. It's a pretty simple and new software
stack that the main motivation is to leverage the Web as application platform.

# Contents

  - [Design](#design) - the architecture behind
  - [Howto](#howto) - set up the system environment, build and run
  - [Contributing](#contributing) - help develop and send patches
  - [License](#license)

# Design

Yocto + Chromium + Ozone-GBM + Crosswalk = Web app

# Howto

This guide will help you to build a bootable image with Lake Bodom. Most of
the toolchain needed to build comes from Yocto Poky and it's expected to use
just a few of your system's dependencies. We use Ubuntu 14.04 LTS (Trusty
Tahr) in particular, but there's no reason to not use any other different
system. Besides, make sure you have **at least** 45 GB of disk space to store
all the sources and a few types of images to be built.

Firstly, you will need anyway a couple of your distribution tools and
development packages:

  ```
  $ sudo apt-get install gawk wget git-core diffstat unzip texinfo
gcc-multilib build-essential chrpath libsdl1.2-dev xterm gyp
  ```

then download the needed stuff:

  ```
  $ mkdir yocto
  $ cd yocto/

  $ git clone http://git.yoctoproject.org/git/poky
  $ git clone http://github.com/crosswalk-project/meta-crosswalk
  $ git clone git@github.com:otcshare/meta-lakebodom.git # yes, you'll need permissions for this one!!!
  ```

now go to poky and checkout the 'dizzy' branch, which is where the new things
are and then jump to its build environment:

  ```
  $ cd poky/
  $ git checkout dizzy
  $ source oe-init-build-env

  ```

You had no conf/local.conf file. A configuration file has therefore been
created for you with some default values but we need still to add the
meta-cross and meta-lakebodom layers in conf/bblayers.conf:

  ```
BBLAYERS ?= " \
  /media/yocto/poky/meta \
  /media/yocto/poky/meta-yocto \
  /media/yocto/poky/meta-yocto-bsp \
  /media/yocto/meta-lakebodom \
  /media/yocto/meta-crosswalk \
  "
  ```

then, set in conf/local.conf the genericx86-64 machine (you could try a
different architecture but we haven't yet):

  ```
MACHINE ?= "genericx86-64"
  ```

besides, still in local.conf we need to pick our simple Web UI to boot the
system into. So add this in the same file:

  ```
IMAGE_INSTALL_append = " ventus"
  ```

Now close the file and let's cook the image: 
  ```
  $ bitbake core-image-lakebodom
  ```  

It will take several hours to download much of the dependencies, build and
etc. Relax now. If everything goes fine, you will have the following directory
with the images built in inside:
  ```
  $ ls tmp/deploy/images/genericx86-64/*.hddimg
  $ tmp/deploy/images/genericx86-64/core-image-lakebodom-genericx86-64-20141009113028.hddimg
  $ tmp/deploy/images/genericx86-64/core-image-lakebodom-genericx86-64.hddimg
  $ cd tmp/deploy/images/genericx86-64/
  ```

Now flash it and boot in your system:
  ```
  $ sudo dd if=core-image-lakebodom-genericx86-64.hddimg of=/dev/sdd
  $ sync 
  $ sudo eject /dev/sdd
  ```

## Contributing

TODO

## License

TODO
