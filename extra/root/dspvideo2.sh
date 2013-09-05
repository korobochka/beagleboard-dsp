#!/bin/sh

gst-launch filesrc location=hd.mp4 ! qtdemux name=demux demux.video_00 ! queue ! h264parse ! dspvdec ! ffmpegcolorspace ! fbdevsink demux.audio_00 ! queue ! aacparse ! dspadec ! alsasink &
