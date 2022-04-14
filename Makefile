refresh-branch:
	git pull origin main --prune
	git branch --merged|egrep -v '\*|develop|main'|xargs git branch -d