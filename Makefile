all:
	ant
clean:
	ant clean
	rm -rf build
	find . -iname "\.*\.swp" -exec rm '{}' \;  
