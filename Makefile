MVN = mvn clean compile assembly:single

all: clean controller client server

controller:
	$(MAKE) target=controller build

client:
	$(MAKE) target=client build

server:
	$(MAKE) target=server build

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
		java -cp $(target)-*.jar $(target).App $(filter-out $@, $(MAKECMDGOALS))

%:
	@:
