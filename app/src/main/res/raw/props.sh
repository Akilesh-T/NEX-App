get_props() {
  AB=`getprop ro.build.ab_update`
  [ "$AB" = "true" ] && SLOT=`getprop ro.boot.slot_suffix`
  ASP=`getprop ro.build.version.security_patch`
  VSP=`getprop ro.vendor.build.security_patch`
  BUILD=`getprop ro.build.version.incremental`
  SKUID=`getprop ro.cda.skuid.id`
  # Credits: Magisk
  grep ' / ' /proc/mounts | grep -qv 'rootfs' && SYSTEM_ROOT="Yes" || SYSTEM_ROOT="No"
}
