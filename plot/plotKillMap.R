library(ggplot2)

# Load the coverage and kill maps.
cov <- read.csv("../mutation_results/covMap.csv")
mut <- read.csv("../mutation_results/killMap.csv")

t <- theme_bw() + theme(plot.title = element_text(hjust = 0.5, face = "bold")) 

# Create two plots (within the same PDF) -- one for the coverage map and one for
# the kill map.
pdf("plot.pdf", width=8, height=8)

ggplot(cov) + geom_point(aes(x=MutantNo, y=TestNo)) +
  ggtitle("Mutation Coverage (coverage map)") + t

ggplot(mut) + geom_point(aes(x=MutantNo, y=TestNo)) +
  ggtitle("Mutant detection (kill map)") + t

dev.off()
