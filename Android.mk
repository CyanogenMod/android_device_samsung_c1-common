ifeq ($(filter-out smdkv310,$(TARGET_BOARD_PLATFORM)),)
    include $(all-subdir-makefiles)
endif
