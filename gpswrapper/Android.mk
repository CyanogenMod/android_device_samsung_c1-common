ifeq ($(BOARD_USES_GPSWRAPPER),true)

LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_MODULE := gps.$(TARGET_BOOTLOADER_BOARD_NAME)

LOCAL_SHARED_LIBRARIES:= \
	liblog \
	libdl

LOCAL_SRC_FILES += \
    gps.c

LOCAL_CFLAGS += \
    -fno-short-enums
   
LOCAL_PRELINK_MODULE := false
LOCAL_MODULE_PATH := $(TARGET_OUT_SHARED_LIBRARIES)/hw

include $(BUILD_SHARED_LIBRARY)

endif
