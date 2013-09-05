#!/bin/sh

gst-launch filesrc location=video_test.mp4 ! qtdemux name=demux demux.video_00 ! queue ! mpeg4videoparse ! dspvdec ! ffmpegcolorspace ! fbdevsink demux.audio_00 ! queue ! aacparse ! dspadec ! alsasink &
