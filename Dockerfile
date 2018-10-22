FROM java:8
ADD out/artifacts/lc101_spring_filter_based_auth_jar/lc101-spring-filter-based-auth.jar lc101-spring-filter-based-auth.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "lc101-spring-filter-based-auth.jar"]

