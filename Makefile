MVN = mvn clean compile assembly:single

db:
	java -jar bin/h2.jar -tcp -baseDir /tmp/h2db

build:
	cd components/$(target) && $(MVN)

run:
	cd components/$(target)/target && \
		java -cp $(target)-*.jar main.App $(filter-out $@, $(MAKECMDGOALS))

start: 
	cd components/$(target) && \
		mvn compile exec:java -Dexec.mainClass="aps.App"

%:
	@:
