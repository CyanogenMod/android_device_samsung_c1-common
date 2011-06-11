ifeq ($(filter-out smdkc210,$(TARGET_BOARD_PLATFORM)),)
    include $(all-subdir-makefiles)
endif
