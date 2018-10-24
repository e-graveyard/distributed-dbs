MVN = mvn clean package

build:
	cd components/$(target) && $(MVN)

run:
	cd components/$(target)/target && \
		java -cp $(target)-*.jar main.App $(filter-out $@, $(MAKECMDGOALS))

%:
	@:
