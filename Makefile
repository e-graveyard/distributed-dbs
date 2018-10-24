MVN = mvn clean package

target:
	cd components/$(C) && $(MVN)

run:
	cd components/$(C)/target && \
		java -cp $(C)-*.jar main.App
