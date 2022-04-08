# Install dependencies

```
sudo apt-get install pandoc texlive-latex-base texlive-fonts-recommended texlive-extra-utils texlive-latex-extra
```

# Create PDF from Markdown

```
pandoc rss-source.md --template=template.tex -o rss.pdf --pdf-engine=xelatex
```