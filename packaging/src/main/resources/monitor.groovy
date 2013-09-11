import org.crsh.text.ui.UIBuilder

class monitor {
    @Command
    public void main(@Option(names=["d","displayOnce"]) Boolean displayOnce) {
        def table = new UIBuilder().table(columns: [1], rows: [1,1,1]) {
            header {
                table(columns:[2,1]) {
                    header(bold: true, fg: black, bg: white) {
                        label("Servlets");
                        label("JVM");
                    }
                    row {
                        eval {
                            execute("jmx find -p Catalina:j2eeType=Servlet,WebModule=*jmx-webapp,* | jmx get WebModule name servletClass requestCount processingTime | sort -f processingTime:desc -f WebModule -f name")
                        }
                        table(columns: [1,2]) {
                            row() {
                                label("Heap:")
                                eval {
                                    execute("jvm heap")
                                }
                            }
                            row() {
                                label("Non heap:")
                                eval {
                                    execute("jvm nonheap")
                                }
                            }
                            (jvm.pools | { name ->
                                row() {
                                    label("$name:")
                                    eval {
                                        execute("jvm pool '$name'")
                                    }
                                }
                            })()
                        }
                    }
                }
            }
            header {
                table(columns: [2,1,1], separator: dashed, rightCellPadding: 1) {
                    header(bold: true, fg: black, bg: white) {
                        label("Webapp");
                        label("Sessions");
                        label("Global");
                    }
                    row {
                        eval {
                            execute("jmx find -p Catalina:j2eeType=WebModule,* | jmx get name displayName stateName processingTime | sort -f name")
                        }
                        eval {
                            execute("jmx find -p Catalina:type=Manager,* | jmx get context sessionCounter sessionCreateRate | sort -f context")
                        }
                        eval {
                            execute("jmx find -p Catalina:type=ThreadPool,name=\\\"http-bio-8080\\\",* | jmx get maxThreads currentThreadCount currentThreadsBusy");
                        }
                    }
                }
            }
            header {
                table(columns: [1], separator: dashed, rightCellPadding: 1) {
                    header(bold: true, fg: black, bg: white) {
                        label("Threads");
                    }
                    row {
                        eval {
                            execute("thread ls");
                        }
                    }
                }
            }
        }

        if (displayOnce) {
            out.show(table);
            out.flush();
        } else {
            context.takeAlternateBuffer();
            try {
                while (!Thread.interrupted()) {
                    out.cls()
                    out.show(table);
                    out.flush();
                    Thread.sleep(1000);
                }
            }
            finally {
                context.releaseAlternateBuffer();
            }
        }

    }
}