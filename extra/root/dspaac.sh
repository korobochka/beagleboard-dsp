#!/bin/sh

gst-launch filesrc location=1.aac ! aacparse ! dspadec ! volume volume=0.3 ! alsasink

