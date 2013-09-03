#!/bin/sh

gst-launch filesrc location=1.aac ! aacparse ! omx_aacdec ! volume volume=0.3 ! alsasink

