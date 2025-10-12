import os

# Set of extensions for which file content should be changed
TEXT_FILE_EXTENSIONS = {".java", ".gradle", ".properties", ".accesswidener", ".json", ".toml"}

def replace_everything(root_dir, old_str, new_str):
    for dirpath, dirnames, filenames in os.walk(root_dir, topdown=False):
        # Process files
        for filename in filenames:
            file_path = os.path.join(dirpath, filename)
            ext = os.path.splitext(filename)[1]

            # Replace in file content if extension is allowed
            if ext in TEXT_FILE_EXTENSIONS:
                try:
                    with open(file_path, 'r', encoding='utf-8') as f:
                        content = f.read()

                    if old_str in content:
                        content = content.replace(old_str, new_str)
                        with open(file_path, 'w', encoding='utf-8') as f:
                            f.write(content)
                        print(f"Updated content: {file_path}")
                except (UnicodeDecodeError, PermissionError) as e:
                    print(f"Skipped content: {file_path} ({e})")

            # Rename file if needed
            if old_str in filename:
                new_filename = filename.replace(old_str, new_str)
                new_path = os.path.join(dirpath, new_filename)
                os.rename(file_path, new_path)
                print(f"Renamed file: {file_path} -> {new_path}")

        # Rename directories
        for dirname in dirnames:
            if old_str in dirname:
                old_dir_path = os.path.join(dirpath, dirname)
                new_dirname = dirname.replace(old_str, new_str)
                new_dir_path = os.path.join(dirpath, new_dirname)
                os.rename(old_dir_path, new_dir_path)
                print(f"Renamed folder: {old_dir_path} -> {new_dir_path}")

if __name__ == "__main__":
    directory_to_search = "."

    modname_input = input("Enter readable name of mod: ").strip()
    modnameNoSpaces = modname_input.replace(" ", "")
    modid = modnameNoSpaces.lower()
    moddescription_input = input("Enter description of mod: ").strip()

    confirm = input("Please confirm by pressing enter to continue or exit script to cancel.")

    replace_everything(directory_to_search, "architecturymod", modid)
    replace_everything(directory_to_search, "Architectury Mod", modname_input)
    replace_everything(directory_to_search, "ArchitecturyMod", modnameNoSpaces)
    replace_everything(directory_to_search, "Mod Description", moddescription_input)
    
    # Update readme
    readme_path = "README.md"
    string_to_write = f"""# {modname_input}

{moddescription_input}"""

    with open(readme_path, 'w', encoding='utf-8') as file:
        file.write(string_to_write)
    print(f"Updated README: {readme_path}")

script_path = os.path.abspath(__file__)
os.remove(script_path)
print(f"Deleted script: {script_path}")
    

