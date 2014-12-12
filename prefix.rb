require 'HTTParty'

class Prefix

  def get_collection
    stack = HTTParty.post( "http://challenge.code2040.org/api/prefix", 
                          :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                          :headers => { 'Content-Type' => 'application/json' } )

    stack["result"]
  end

  def find_strings
    collection = get_collection
    print(collection)
    strings = collection["array"]
    pref = collection["prefix"]
    array_of_strings = Array.new
    put_found_strings_in_array(strings, pref, array_of_strings)
  end

  def put_found_strings_in_array(strings, pref, array_of_strings)
    strings.each do |string|
      if !string.start_with?(pref)
        array_of_strings << string
      end
    end
    array_of_strings
  end

  def validate_prefix
    result = HTTParty.post( "http://challenge.code2040.org/api/validateprefix",
                                   :body => { "token" => "8EBDqKCWgK", "array" => find_strings }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

prefix = Prefix.new
prefix.validate_prefix
