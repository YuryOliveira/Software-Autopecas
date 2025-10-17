# Software-Autopecas
## Como clonar

### 1 - Instale o git

https://git-scm.com/downloads

### 2 - Abra o terminal na area de trabalho e clone o repositório

git clone https://github.com/YuryOliveira/Software-Autopecas.git

### 3 - Tenha certeza que você esta na pasta do projeto (Sft-Aut)

## Criar uma nova branch do projeto

### git checkout -m ->branchName<-

## Como subir as modificações

### 1 - Suba as modificações para seu git local e depois faça o push

git add .
git commit -m "Commit message - describe what was done in the code"
git push

### possivel erro:

Um possível erro seria o

*fatal: The current branch branchTest has no upstream branch.
To push the current branch and set the remote as upstream, use

    git push --set-upstream origin branchTest

To have this happen automatically for branches without a tracking upstream, see 'push.autoSetupRemote' in 'git help config'.*

Para resolver esse problema, basta copiar o código que o próprio erro sugere (git push --set-upstream origin [nome da branch])
