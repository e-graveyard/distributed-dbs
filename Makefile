MVN = mvn clean compile assembly:single

build:
	cd components/$(target) && $(MVN)

run:
	cd components/$(target)/target && \
		java -cp $(target)-*.jar main.App $(filter-out $@, $(MAKECMDGOALS))

%:
	@:
