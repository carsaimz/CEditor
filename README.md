# CEditor - Editor de Código

**CEditor** é um editor de código e gerenciador de arquivos avançado para Android, projetado para oferecer uma experiência de desenvolvimento completa diretamente no seu dispositivo móvel.

## ✨ Funcionalidades Principais

- **Editor de Código Potente**: Baseado no SoraEditor, com suporte a realce de sintaxe para diversas linguagens.
- **Gerenciador de Arquivos**: Navegação completa, suporte a armazenamento interno e externo (SAF).
- **Ferramentas de Mídia**: Visualizador de imagens integrado, player de áudio e editor básico de imagens.
- **Integração Git**: Clone repositórios diretamente para o seu dispositivo.
- **Utilitários Avançados**: 
  - Visualizador de HTML com execução em tempo real.
  - Conversores de texto (Base64, Binary, HEX, Leetspeak).
  - Terminal integrado.
  - Visualizador de arquivos de arquivo (zip, etc).
  - Cálculo de Checksum (Hash).

## 🌍 Idiomas Suportados

- **Inglês** (Padrão)
- **Português** (Brasil)

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Plataforma**: Android
- **Bibliotecas Principais**:
  - [SoraEditor](https://github.com/Rosemoe/SoraEditor) - Núcleo do editor de código.
  - [JGit](https://www.eclipse.org/jgit/) - Operações Git.
  - [Glide](https://github.com/bumptech/glide) - Carregamento de imagens.

## 🚀 Como Contribuir

1. Faça um fork do projeto.
2. Crie uma branch para sua funcionalidade (`git checkout -b feature/nova-funcionalidade`).
3. Faça o commit de suas alterações (`git commit -am 'Adiciona nova funcionalidade'`).
4. Faça o push para a branch (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.

## 🛠️ Build e Release Automáticos

Este projeto utiliza **GitHub Actions** para automatizar o processo de build e release.

### Workflows Disponíveis:
- **Android Build**: Gera APK e AAB de depuração (debug). Pode ser disparado manualmente na aba "Actions".
- **Android Release**: Gera APK e AAB assinados e cria um novo Release no GitHub.

### Configuração de Secrets:
Para que o workflow de **Release** funcione corretamente, você deve configurar os seguintes Secrets no seu repositório:
- `KEYSTORE_FILE`: O conteúdo do arquivo `.keystore` ou `.jks` codificado em **Base64**.
- `KEYSTORE_PASSWORD`: Senha do arquivo keystore (padrão: `carsaidev`).
- `KEY_ALIAS`: Alias da chave (padrão: `carsaidev`).
- `KEY_PASSWORD`: Senha da chave (padrão: `carsaidev`).

---
Desenvolvido por [carsaimz](https://github.com/carsaimz)
