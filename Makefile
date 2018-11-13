MVN = mvn clean compile assembly:single

build:
	cd components/$(target) && $(MVN)

clean:
	cd components && \
		rm -rf client/target && \
		rm -rf controller/target && \
		rm -rf server/target

db:
	java -jar bin/h2.jar -tcp -pg -web -baseDir ~/h2db

run:
	cd components/$(target)/target && \
		java -cp $(target)-*.jar main.App $(filter-out $@, $(MAKECMDGOALS))

start:
	cd components/$(target) && \
		mvn compile exec:java -Dexec.mainClass="aps.App"

%:
	@:
