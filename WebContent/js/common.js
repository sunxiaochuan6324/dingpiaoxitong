function numbox() {
	if (event.keyCode == 32 || event.keyCode == 13 || event.keyCode == 9
			|| event.keyCode == 8 || event.keyCode == 46 || event.keyCode == 37
			|| event.keyCode == 39 || event.keyCode == 190
			|| event.keyCode == 189 || event.keyCode == 110
			|| event.keyCode == 109)
		return true;
	if (event.keyCode >= 48 && event.keyCode <= 57)
		return true;
	if (event.keyCode >= 96 && event.keyCode <= 105)
		return true;
	return false;
}