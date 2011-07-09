LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	main_mediaserver.cpp 

LOCAL_SHARED_LIBRARIES := \
	libaudioflinger \
	libcameraservice \
	libmediaplayerservice \
	libmediayamahaservice \
	libutils \
	libbinder

base := $(TOP)/frameworks/base

LOCAL_C_INCLUDES := \
    $(base)/services/audioflinger \
    $(base)/services/camera/libcameraservice \
    $(base)/media/libmediaplayerservice

LOCAL_MODULE:= mediaserver.smdkv310
LOCAL_MODULE_STEM := mediaserver
LOCAL_MODULE_TAGS := optional

include $(BUILD_EXECUTABLE)
