HOMEPAGE = "https://github.com/docker/buildx"
SUMMARY = "Docker CLI plugin for extended build capabilities with BuildKit"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV_buildx = "fa4461b9a1ec45c23d1b9e32dee0d0a8ed29900b"
SRC_URI = "\
	git://github.com/docker/buildx.git;branch=v0.18;name=buildx;destsuffix=git/buildx;protocol=https \
	file://0001-buildx-use-GO-instead-of-go-and-remove-mod-vendor.patch \
	"

GO_IMPORT = "import"
S = "${WORKDIR}/git/buildx"

inherit go goarch pkgconfig

export GO_LINKSHARED

DOCKER_VERSION = "25.0.3"
PV = "${DOCKER_VERSION}+git${SRCREV_buildx}"

do_compile() {
	cd ${S}
	make build
}

do_install() {
	mkdir -p ${D}/${libexecdir}/docker/cli-plugins
	cp ${WORKDIR}/git/buildx/bin/build/docker-buildx ${D}/${libexecdir}/docker/cli-plugins/docker-buildx
	install -d ${D}/${libexecdir}/docker/cli-plugins
}

FILES:${PN} += "${libexecdir}/docker/cli-plugins/docker-buildx"