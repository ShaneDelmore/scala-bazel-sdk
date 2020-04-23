load("@io_bazel_rules_scala//scala:scala.bzl", "scala_binary", "scala_library", "scala_test")

scala_binary(
    name = "MainScala",
    srcs = glob(["*.scala"]),
    main_class = "MainScala",
)

java_binary(
    name = "MainJava",
    srcs = ["MainJava.java"],
    main_class = "MainJava",
    deps = [],
)
