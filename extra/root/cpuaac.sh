#!/bin/sh

gst-launch filesrc location=1.aac ! aacparse ! faad ! volume volume=0.1 ! alsasink

