# CEditor - Editor de Código 🚀

[![Android Build](https://github.com/carsaimz/CEditor/actions/workflows/android_build.yml/badge.svg)](https://github.com/carsaimz/CEditor/actions/workflows/android_build.yml)
[![Android Release](https://github.com/carsaimz/CEditor/actions/workflows/android_release.yml/badge.svg)](https://github.com/carsaimz/CEditor/actions/workflows/android_release.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)

**CEditor** é um editor de código e gerenciador de arquivos avançado para Android, projetado para oferecer uma experiência de desenvolvimento completa diretamente no seu dispositivo móvel.

---

## ✨ Funcionalidades Principais

- 💻 **Editor de Código Potente**: Baseado no SoraEditor, com suporte a realce de sintaxe para diversas linguagens.
- 📂 **Gerenciador de Arquivos**: Navegação completa, suporte a armazenamento interno e externo (SAF).
- 🖼️ **Ferramentas de Mídia**: Visualizador de imagens integrado, player de áudio e editor básico de imagens.
- 🐙 **Integração Git**: Clone repositórios diretamente para o seu dispositivo.
- 🛠️ **Utilitários Avançados**: 
  - 🌐 Visualizador de HTML com execução em tempo real.
  - 🔐 Conversores de texto (Base64, Binary, HEX, Leetspeak).
  - 🐚 Terminal integrado.
  - 📦 Visualizador de arquivos comprimidos (zip, etc).
  - 🔢 Cálculo de Checksum (Hash).

---

## 🌍 Idiomas Suportados

O CEditor agora suporta troca dinâmica de idioma nas configurações:
- 🇺🇸 **Inglês** (Padrão)
- 🇧🇷 **Português** (Brasil)

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java
- **Plataforma**: Android (SDK 21+)
- **Bibliotecas Principais**:
  - [SoraEditor](https://github.com/Rosemoe/SoraEditor) - Núcleo do editor de código.
  - [JGit](https://www.eclipse.org/jgit/) - Operações Git.
  - [Glide](https://github.com/bumptech/glide) - Carregamento de imagens.

---

## 🚀 Build e Release Automáticos

O CEditor agora possui um pipeline de CI/CD **100% funcional**. O projeto foi recuperado de um estado de compilação quebrado, com a restauração de layouts ausentes e correção de dependências.

Este projeto utiliza **GitHub Actions** para automatizar o processo de build e release.

### Workflows Disponíveis:
- **Android Build**: Gera APK e AAB de depuração (debug), cria um **Pré-release** automático e gera um **Changelog**.
- **Android Release**: Gera APK e AAB assinados e cria um novo Release oficial no GitHub.

### 📥 Downloads
Para baixar a versão mais recente, acesse a aba [Releases](https://github.com/carsaimz/CEditor/releases).

---

## 🤝 Como Contribuir

Adotamos o padrão **Conventional Commits**:
- `feat`: Novas funcionalidades.
- `fix`: Correção de bugs.
- `docs`: Alterações na documentação.
- `style`: Alterações de formatação (espaços, vírgulas, etc).
- `refactor`: Refatoração de código.
- `chore`: Atualização de tarefas de build, pacotes, etc.

---
Desenvolvido com ❤️ por [carsaimz](https://github.com/carsaimz)
